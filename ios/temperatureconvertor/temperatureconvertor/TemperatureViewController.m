//
//  TemperatureViewController.m
//  temperatureconvertor
//
//  Created by pushkar on 10/8/13.
//  Copyright (c) 2013 pjmodi. All rights reserved.
//

#import "TemperatureViewController.h"

@interface TemperatureViewController ()
@property (weak, nonatomic) IBOutlet UITextField *fahrenheitTextField;
@property (weak, nonatomic) IBOutlet UITextField *celsiusTextField;

- (IBAction)onTap:(id)sender;
- (IBAction)onEditBeginFahrenheit:(id)sender;
- (IBAction)onEditBeginCelsius:(id)sender;
- (IBAction)onTouchDownConvertButton:(id)sender;

- (void)resetUserInput;
- (void)calculateConversion;

@end

@implementation TemperatureViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
		self.title = @"Temperature Convertor";
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)onTap:(id)sender {
	[self.view endEditing:YES];
}

- (IBAction)onEditBeginFahrenheit:(id)sender {
	[self resetUserInput];
}

- (IBAction)onEditBeginCelsius:(id)sender {
	[self resetUserInput];
}

- (IBAction)onTouchDownConvertButton:(id)sender {
	[self calculateConversion];
}

- (void)resetUserInput {
	self.fahrenheitTextField.text = @"";
	self.celsiusTextField.text = @"";
}

- (void)calculateConversion {
	[self.fahrenheitTextField resignFirstResponder];
	[self.celsiusTextField resignFirstResponder];
	
	if ([self.fahrenheitTextField.text length] > 0) {
		float fahrenheitValue = [self.fahrenheitTextField.text floatValue];
		float celsiusValue = (((fahrenheitValue - 32.0) * 5.0) / 9.0);
		self.celsiusTextField.text = [NSString stringWithFormat:@"%.1f", celsiusValue];
	} else if ([self.celsiusTextField.text length] > 0) {
		float celsiusValue = [self.celsiusTextField.text floatValue];
		float fahrenheitValue = ((celsiusValue * 9.0)/5.0) + 32.0;
		self.fahrenheitTextField.text = [NSString stringWithFormat:@"%.1f", fahrenheitValue];
	}
}

@end
