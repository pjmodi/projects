//
//  TipViewController.m
//  tipcalculator
//
//  Created by pushkar on 9/18/13.
//  Copyright (c) 2013 pjmodi. All rights reserved.
//

#import "TipViewController.h"

@interface TipViewController ()
@property (weak, nonatomic) IBOutlet UITextField *txtBillAmount;
@property (weak, nonatomic) IBOutlet UILabel *lblTipAmount;
@property (weak, nonatomic) IBOutlet UILabel *lbltotalAmount;
@property (weak, nonatomic) IBOutlet UISegmentedControl *segTipControl;

- (IBAction)onTap:(id)sender;
- (void)updateValues;
@end

@implementation TipViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
		self.title = @"Tip Calculator";
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
	[self updateValues];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)onTap:(id)sender {
	[self.view endEditing:YES];
	[self updateValues];
}

- (void)updateValues {
	float billAmount = [self.txtBillAmount.text floatValue];
	NSArray *tipValues = @[@(0.1), @(0.15), @(0.2)];
	float tipAmount = billAmount * [tipValues[self.segTipControl.selectedSegmentIndex] floatValue];
	float totalAmount = billAmount + tipAmount;
	
	self.lblTipAmount.text = [NSString stringWithFormat:@"$%0.2f", tipAmount];
	self.lbltotalAmount.text = [NSString stringWithFormat:@"$%0.2f", totalAmount];
}

@end
