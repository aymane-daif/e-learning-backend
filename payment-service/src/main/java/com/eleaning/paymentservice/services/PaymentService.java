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

    public SaleDto buyCourse(PaymentDto paymentDto, String userEmail, Long courseId)
            throws Exception {


        CourseDto course = courseExists(courseId);
        UserDto user = userExists(userEmail);
        Sale sale = new Sale();
        sale.setUserId(user.getUserId());
        sale.setCourseId(courseId);
        sale.setPrice(course.getPrice());
        sale.setDate(new Date());
        stripePayment(paymentDto);

        saleRepository.save(sale);
        return new SaleDto(sale);
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

}
