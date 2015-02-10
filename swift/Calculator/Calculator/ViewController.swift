//
//  ViewController.swift
//  Calculator
//
//  Created by ronik on 2/8/15.
//  Copyright (c) 2015 Pushkar Modi. All rights reserved.
//

import UIKit
import Darwin

class ViewController: UIViewController {
    
    @IBOutlet weak var display: UILabel!
    
    var userIsInTheMiddleOfTypingANumber = false
    var decimalsUsed = 0
    var operandStack = Array<Double>()
    
    @IBAction func appendDigit(sender: UIButton) {
        let digit = sender.currentTitle!
        
        if digit == "." {
            if decimalsUsed > 0 {
                return
            } else {
                decimalsUsed++
            }
        }
        
        if userIsInTheMiddleOfTypingANumber {
            display.text = display.text! + digit
        } else {
            display.text = digit
            userIsInTheMiddleOfTypingANumber = true
        }
    }
    
    @IBAction func pi(sender: UIButton) {
        let pi = M_PI
        
        if userIsInTheMiddleOfTypingANumber {
            enter()
        }
        
        display.text = "\(pi)"
        enter()
    }
    
    @IBAction func operate(sender: UIButton) {
        let operation = sender.currentTitle!
        
        if userIsInTheMiddleOfTypingANumber {
            enter()
        }
        
        switch operation {
            case "×": performOperation {$0 * $1}
            case "÷": performOperation {$1 / $0}
            case "+": performOperation {$0 + $1}
            case "−": performOperation {$1 - $0}
            case "√": performOperation {sqrt($0)}
            case "sin": performOperation {sin($0)}
            case "cos": performOperation {cos($0)}
            default: break
        }
    }
    
    func performOperation(operation: (Double, Double) -> Double) {
        if operandStack.count >= 2 {
            displayValue = operation(operandStack.removeLast(), operandStack.removeLast())
            enter()
        }
    }
    
    func performOperation(operation: Double -> Double) {
        if operandStack.count >= 1 {
            displayValue = operation(operandStack.removeLast())
            enter()
        }
    }
    
    @IBAction func enter() {
        userIsInTheMiddleOfTypingANumber = false
        operandStack.append(displayValue)
        println("operandStack = \(operandStack)")
    }
    
    var displayValue: Double {
        get {
            return NSNumberFormatter().numberFromString(display.text!)!.doubleValue
        }
        set {
            display.text = "\(newValue)"
            userIsInTheMiddleOfTypingANumber = false
        }
    }
}