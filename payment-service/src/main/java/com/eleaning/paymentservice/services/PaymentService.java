package com.eleaning.paymentservice.services;

import com.eleaning.paymentservice.Exceptions.CourseDoesntExist;
import com.eleaning.paymentservice.Exceptions.StripeException;
import com.eleaning.paymentservice.Exceptions.UserDoesntExist;
import com.eleaning.paymentservice.dtos.CourseDto;
import com.eleaning.paymentservice.dtos.PaymentDto;
import com.eleaning.paymentservice.dtos.SaleDto;
import com.eleaning.paymentservice.dtos.UserDto;
import com.eleaning.paymentservice.entities.Sale;
import com.eleaning.paymentservice.repositories.SaleRepository;
import com.eleaning.paymentservice.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PaymentService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private StripeClient stripeClient;

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    public static ModelMapper mapper = new ModelMapper();

    public List<SaleDto> buyCourse(PaymentDto paymentDto, String userEmail)
            throws Exception {

        List<SaleDto> saleDtoList = new ArrayList<>();

        for (Long courseId : paymentDto.getCoursesIds()) {
            CourseDto course = courseExists(courseId);
            Sale sale = new Sale();
            UserDto user = userExists(userEmail);
            sale.setUserId(user.getUserId());
            sale.setCourseId(courseId);
            sale.setPrice(course.getPrice());
            sale.setDate(new Date());
            saleRepository.save(sale);
            saleDtoList.add(new SaleDto(sale));
        }

        stripePayment(paymentDto);
        return saleDtoList;

    }

    public void stripePayment(PaymentDto paymentDto) throws StripeException {
        try{
            stripeClient.chargeNewCard(paymentDto.getToken(), paymentDto.getPrice());
        }catch (Exception e){
            throw new StripeException();
        }

    }

    public CourseDto courseExists(Long courseId) throws CourseDoesntExist {
        try{
            CourseDto courseDto = courseService.getCourseById(courseId);
            if(courseDto == null){
                throw new CourseDoesntExist();
            }
            return courseDto;
        }catch (Exception exception){
            throw new CourseDoesntExist();
        }
    }

    public UserDto userExists(String userEmail) throws UserDoesntExist {
        try{
            UserDto user = userService.getUserByEmail(userEmail);
            return user;
        } catch (Exception e) {
            throw new UserDoesntExist();
        }
    }

    public Optional<List<SaleDto>> getAllSalesOfUsers(Long userId) {
        List<SaleDto> saleDtos = saleRepository.findAllByUserId(userId)
                .stream().map(sale -> toSaleDto(sale)).collect(Collectors.toList());
        return Optional.of(saleDtos);
    }

    private SaleDto toSaleDto(Sale sale) {
        return mapper.map(sale, SaleDto.class);
    }
}
