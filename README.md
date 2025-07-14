# Calculator App

A simple calculator application for Android built with Kotlin.

## Features

- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Decimal point input
- Clear button to reset calculations
- Error handling for invalid expressions
- Clean and intuitive user interface

## Screenshots

[Insert screenshots of the app here]

## Installation

1. Clone this repository
2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device

## Usage

1. Enter numbers by tapping the digit buttons
2. Use the decimal point button (.) for decimal numbers
3. Select an operation (+, -, *, /)
4. Press the equals (=) button to see the result
5. Use the CLR button to clear the current calculation

## Code Structure

- `MainActivity.kt`: Contains all the calculator logic
- `activity_main.xml`: Defines the user interface layout

## Implementation Details

The calculator implements:
- State management for tracking decimal points and operators
- Input validation to prevent invalid expressions
- Error handling for division by zero and other calculation errors
- Clean display formatting (removing .0 for integer results)

## Known Limitations

- Supports only two-operand operations
- No support for parentheses or complex expressions
- No memory functions (M+, M-, MR, MC)
- No percentage calculation

## Future Improvements

- Add support for more complex expressions
- Implement memory functions
- Add scientific calculator features
- Support landscape orientation with additional functions
- Add theme customization options
