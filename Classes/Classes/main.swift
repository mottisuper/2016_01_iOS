//
//  main.swift
//  Classes
//
//  Created by Elad Lavi on 10/02/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//

import Foundation

class Person {
    private var _firstName: String!;
    private var _lastName: String;
    lazy var idNumber:Int = 123;
    
    private var _cities:[String] = ["Tel Aviv", "Rishon", "Ashkelon", "Ashdon", "Kfar Saba", "Ra'ana"];
    
    static var numberOfPersons:Int = 0;
    
    init(firstName: String, lastName: String){
        print("person is being created.");
        _firstName = firstName;
        _lastName = lastName;
    }
    
    init(){
        //_firstName = "First";
        _lastName = "Last";
    }
    
    
    var age:Int = 0{
        willSet{
            print("about to change age");
        }
        didSet{
            print("age was just changed");
        }
    }
    func description()->String{
        return "I'm \(self._firstName) and I'm \(self.age) years old";
    }
    
    var name:String{
        get{
            if let theFirstName = _firstName{
                return theFirstName + " " + _lastName;
            }else{
                return _lastName;
            }
        }
    }
    
    var firstName:String?{
        get{
            return _firstName;
        }
        set{
            _firstName = newValue;
        }
    }
    
    var lastName:String{
        get{
            return _lastName;
        }
        set{
            print("about to change lastName");
            _lastName = newValue;
            print("lastName was just changed");
        }
    }
    
    var dogAge:Int{
        get{
            return age * 7;
        }
        set{
            if newValue > 0{
                age = newValue / 7;
            }
        }
    }
    
    func printIdNumber(){
        print("my id number is \(self.idNumber)");
    }
    
    static func getNumberOfPersons()->Int{
        return Person.numberOfPersons;
    }
    
    subscript(index: Int)->String{
        get{
            if index < _cities.count{
                return _cities[index];
            }else{
                return "invalid index";
            }
            
        }
        set{
            _cities[index] = newValue;
        }
    }
    
    func setCity(index: Int, city: String){
        _cities[index] = city;
    }
    
    func getCity(index: Int)->String{
        return _cities[index];
    }
    
    var cities:[String]{
        get{
            return _cities;
        }
        set{
            _cities = newValue;
        }
    }
    

}

class Employee: Person {
    var employeeNumber:Int = 123;
    
    override func description() -> String {
        return super.description() + ", and my employee number is \(self.employeeNumber)";
    }
}



Person.numberOfPersons = 3;
var p1 = Person(firstName: "Elad", lastName: "Lavi");
p1.printIdNumber();
p1.age = 18;
var p2 = Person();
p2.firstName = "Elad";
p2.age = 18;
p2.age++;
var f = p1 === p2;
print("the value of f is \(f)");
let n = p1.name;
print(p1[4]);
var e1:Employee = Employee();

class Rectangle {
    private var _width:Int = 0;
    private var _height:Int = 0;
    func getArea()->Int{
        return _width * _height;
    }
    func getPerimeter()->Int{
        return _width*2 + _height*2;
    }
    
    var width:Int{
        get{
            return _width;
        }
        set{
            _width = newValue;
        }
    }
    
    var height:Int{
        get{
            return _height;
        }
        set{
            _height = newValue;
        }
    }
    
    func getPrice()->Int{
        return 3 * getArea();
    }
}

class Square: Rectangle {
    var side:Int{
        get{
            return _height;
        }
        set{
            _height = newValue;
            _width = newValue;
        }
    }
    
    override var width:Int{
        get{
            return _width;
        }
        set{
            side = newValue;
        }
    }
    override var height:Int{
        get{
            return _width;
        }
        set{
            side = newValue;
        }
    }
    
    override func getArea() -> Int {
        return super.getArea()+1;
    }
    
    
}
var s:Rectangle = Square();
s.height = 3;
s.width = 3;
//s.side = 3;
print("price \(s.getPrice())");

//var mySquare:Square? = s as? Square; //if I am not sure that s points to a square. if it doesn't, then the pointer will be nil.

if s is Square{
    var mySquare:Square = s as! Square; //only if I'm certain that the pointer points to a Square;
}




