//
//  main.swift
//  closures
//
//  Created by Elad Lavi on 07/02/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//

import Foundation


func sortArray(inout arr: [Int], sortBy: (Int,Int)->Bool){
    // a   b   c
    var isSorted = false;
    var counter = arr.count - 1;
    while(!isSorted){
        isSorted = true;
        for(var i=0;i<counter;i++){
            if(sortBy(arr[i],arr[i+1])){
                isSorted = false;
                let temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        counter--;
    }
}


func myCompareRule(x: Int, y: Int)->Bool{
    return x > y;
}



var arr = [9,5,2,6,8];
sortArray(&arr, sortBy: myCompareRule);
for i in 0..<arr.count{
    print("\(arr[i])");
}

//closure:
sortArray(&arr, sortBy:  { (x: Int, y: Int) -> Bool in
    return x > y;
});

let myComparsion = { (x: Int, y: Int) -> Bool in
    return x > y;
};
print(myComparsion(4,3));




