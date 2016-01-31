//
//  main.swift
//  Enums
//
//  Created by Elad Lavi on 31/01/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//

import Foundation

//what is enum ?
//when you define an enum, you actually define a type. a special type that contains a fixed list of values. i.e :

enum ComputerState{
    case Sleep;
    case Active;
    case Hibernate;
    case Shutdown;
}

let myComputerState = ComputerState.Sleep;
if(myComputerState == ComputerState.Shutdown){
    print("your machine is turned off");
}

if(myComputerState == .Shutdown){
    print("your machine is turned off");
}

var yourComputerState: ComputerState;
yourComputerState = .Sleep;


//switch in Swift doesn't require break after each case. In Java, without the break, we will have a fallthrough.
//also switch in swift must be exhaustive 
switch(yourComputerState){
case ComputerState.Sleep:
    print("sleep");
case ComputerState.Active:
    print("active");
case .Hibernate:
    print("hibernate");

default:
    print("default");
}
print(yourComputerState.hashValue);
print(ComputerState.Sleep.hashValue);












