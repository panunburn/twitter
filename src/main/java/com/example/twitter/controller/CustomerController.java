package com.example.Twitter.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.Twitter.model.Customer;
import com.example.Twitter.model.CustomerUI;
import com.example.Twitter.model.Tweet;
import com.example.Twitter.model.TweetUI;
import com.example.Twitter.repository.CustomerRepository;

import com.example.Twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    TweetRepository tweetRepository;

    //old
//	CustomerController(CustomerRepository repository) {
//		this.repository = repository;
//	}

    @GetMapping("/bulkcreate")
    public String bulkcreate(){
        // save a single Customer
        repository.save(new Customer("Rajesh", "Bhojwani"));

        // save a list of Customers
        repository.saveAll(Arrays.asList(new Customer("Salim", "Khan")
                , new Customer("Rajesh", "Parihar")
                , new Customer("Rahul", "Dravid")
                , new Customer("Dharmendra", "Bhojwani")));

        return "Customers are created";
    }
    @PostMapping("/create")
    public String create(@RequestBody CustomerUI customer){
        // save a single Customer
        repository.save(new Customer(customer.getFirstName(), customer.getLastName()));

        return "Customer is created";
    }
    @GetMapping("/findall")
    public List<CustomerUI> findAll(){

        List<Customer> customers = repository.findAll();
        List<CustomerUI> customerUI = new ArrayList<>();

        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }

    @RequestMapping("/search/{id}")
    public String search(@PathVariable long id){
        String customer = "";
        customer = repository.findById(id).toString();
        return customer;
    }

    @RequestMapping("/searchbyfirstname/{firstname}")
    public List<CustomerUI> fetchDataByFirstName(@PathVariable String firstname){

        List<Customer> customers = repository.findByFirstName(firstname);
        List<CustomerUI> customerUI = new ArrayList<>();

        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }

    @RequestMapping("/searchbylastname/{lastname}")
    public List<CustomerUI> fetchDataByLastName(@PathVariable String firstname){

        List<Customer> customers = repository.findByLastName(firstname);
        List<CustomerUI> customerUI = new ArrayList<>();

        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }

    @RequestMapping("/searchname/{name}")
    public List<CustomerUI> fetchDataByContraining(@PathVariable String name){

        List<Customer> customers = repository.fetchCustomers(name);
        System.out.println(customers.size());
        List<CustomerUI> customerUI = new ArrayList<>();

        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }

    @RequestMapping("/searchname2/{name}")
    public List<CustomerUI> fetchDataByContraining2(@PathVariable String name){

        List<Customer> customers = repository.findByFirstNameLike(name);
        System.out.println(customers.size());
        List<CustomerUI> customerUI = new ArrayList<>();

        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }

    @GetMapping("/findalltweets")
    public List<Tweet> findAllTweets(){
        List<Tweet> tweets = tweetRepository.findAll();
        return tweets;
    }

    @RequestMapping("/searchTweet/{id}")
    public TweetUI searchTweet(@PathVariable long id){
        Tweet tweet = (Tweet) tweetRepository.findById(id);
        return new TweetUI(tweet.getContent());
    }

    @RequestMapping("/searchcontent/{str}")
    public List<TweetUI> fetchTweetByContraining(@PathVariable String str){

        List<Tweet> tweets = tweetRepository.fetchTweetsWithContent(str);
        List<TweetUI> tweetsUI = new ArrayList<>();

        for (Tweet tweet : tweets) {
            tweetsUI.add(new TweetUI(tweet.getContent()));
        }

        return tweetsUI;
    }

    @PostMapping("/createtweet")
    public String create(@RequestBody Tweet tweet){
        // save a single Customer
        tweetRepository.save(new Tweet(3, tweet.getContent()));

        return "Tweet is created";
    }
}