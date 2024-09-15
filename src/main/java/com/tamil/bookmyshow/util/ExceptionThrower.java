//package com.tamil.bookmyshow.util;
//
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import com.tamil.bookmyshow.dto.City;
//import com.tamil.bookmyshow.exception.CityNotFoundException;
//import com.tamil.bookmyshow.exception.EmailException;
//import com.tamil.bookmyshow.exception.InvalidDetailException;
//import com.tamil.bookmyshow.exception.PasswordException;
//import com.tamil.bookmyshow.exception.TimeException;
//
//public final class ExceptionThrower {
//	
//	private ExceptionThrower() {}
//	
//	public static void passwordChecker(String password) {
//		if(password==null||password.isBlank())throw new PasswordException("Password is null");
//		else if(password.length()<=4)throw new PasswordException("Password is less than 5 characters");
//		
//	}
//	public static void emailChecker(String email) {
//		if(email==null)throw new EmailException("Email is Null");
//		if(email.length()<=10)throw new EmailException("Invalid Email address");
//		for(int i=0;i<email.length()-10;i++) {
//			if(email.charAt(i)==' ')throw new EmailException("Invalid Email address (Character space (' ') is not allowed in email)");
//		}
//		String temp="@gmail.com";
//		String temp2=email.substring(email.length()-10);
//		boolean flag=temp.equals(temp2);
//		if(!flag)throw new EmailException("Invalid Email address Excepted email :["+email.substring(0, email.length()-10)+temp+"] but Provided ["+email+"]");
//	}
//	public static void idChecker(int id) {
//		if(id <=0)throw new InvalidDetailException("Id value should be greater than 0");
//	}
//	public static void nameChecker(String name) {
//		if(name==null)throw new InvalidDetailException("Name is null");
//		if(name.length()<=2)throw new InvalidDetailException("Invlid Name : name has only "+name.length()+" character");
//		   for (int i=0;i<name.length();i++){
//		        if((name.charAt(i)>=65&&name.charAt(i)<=90)||(name.charAt(i)>=97&&name.charAt(i)<=122)){}
//		        else throw new InvalidDetailException("Name should contain only alphabets but it contains "+name.charAt(i));
//		   	}
//	}
//	public static void dateChecker(LocalDate date) {
//		if(date==null)throw new InvalidDetailException("Date is null");
//		if(date.isBefore(LocalDate.now()))throw new InvalidDetailException("Invalid Date [Entered the past date :"+date+"]where as  Current Date is ["+LocalDate.now()+"]");
//	}
//	public static void timeChecker(LocalTime time) {
//		if(time==null) throw new TimeException("Time is null");
//	}
//	public static void cityChecker(City city) {
//		if(city==null) throw new InvalidDetailException("city is null");
//	 City[] array=City.values();
//	 boolean found=false;
//	 for(City c:array) {
//		 if(c.equals(city))found=true;
//	 }
//	 if(!found)throw new CityNotFoundException("Entered City is not Found");
//	}
//	
//}
