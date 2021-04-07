________________


# Practices 2
## Overview
In these practices, you use JShell to explore Java primitive types and organize program flow with `if/else` and `switch` flow control constructs.

```bash
jshell> char x = 'a', y = 'b';
x ==> 'a'
y ==> 'b'

jshell> if (x < y) {
    ...>
```

## Practice 2-1: Manipulate with Primitive Types
### Overview
In this practice, you declare primitive variables, use assignment, and arithmetic operators.

### Assumptions
- JDK 11 is installed.
- JDK executables path is configured.
- The practices folder structure is created.

### Tasks
1. Prepare the practice environment.
    1. Open the terminal window.
    2. Change folder to `/home/oracle/labs/practice2`. (Your path for the cd command may vary depending on which folder is your current folder.).\
       `cd /home/oracle/labs/practice2`
    3. Invoke the Shell tool: `jshell`\
       **Notes:** In case you need to leave JShell, enter the `/exit` command. You can also enter `/help` to get a list of JShel commands.

2. Declare numeric primitives and perform type-casting and arithmetic operations.
    1. Declare three byte variables `x`, `y`, and `z`. Immediately upon declaration, initialize these
variables as first three prime numbers (2, 3, and 5).
       ```java
       byte x = 2, y = 3, z = 5;
       ```

    2. Recalculate a value of `z` by adding `x` and `y` values.
       ```java
       z = (byte) (x+y);
       ```
       **Note:** Remember that any arithmetic operation on types smaller than `int` results in an
`int` type. Therefore, type-casting will be required.

    3. Divide x by y and assign the result to a float variable `a`:
       ```java
       float a = (float) x/y;
       ```
       **Notes:**
        - Without casting either `x` or `y` to float type, division operation would produce an
`int` result.
        - You need to cast not the overal result of the division `(float) (x/y)`, but rather
any of the participants, in order to get actual floating point result.

    4. Divide `x` by `y` and assign a result to a `double` variable `b`:
       ```java
       double b = (double) x/y;
       ```
       **Note:** Observe how `double` number 64-bit capacity allows for the allocation of more
decimal digits of precision.

    5. Assign variable a value (which is a float type variable created in an earlier practice
step) to variable b and observe the rounding side effects:
       ```java
       b = a;
       ```
       **Note:** Although the assignment of a smaler type value (float variable) to a larger type (double variable) works and does not require type-casting, there is a rounding problem that occurs beyond the original 32-bit capacity of a number. This rounding problem is in fact already resolved by existing Java API classes (for example, `BigDecimal`). However, this is covered later in the lesson titled "Text, Date, Time, and Numeric Objects".
       
    6. Round variable `b` value to 3 decimal digits and assign the result to float variable `c`.
       **Hints**
        - Use the `round` method of a Math class to perform rounding.
        - The `round` method always rounds values to a whole number(int or long).
Therefore, to get 3 decimal digits, you need to multiply the number by a 10 (1000) and then divide the result of the founding by the same amount.
        - Remember that the result of rounding is a whole number(int or long) Therefore, make sure that when you divide it by the required amount, at least one participant of this operation is a float number, in order to ensure that you get a `float` type result.
       ```java
       float - Math.round(b * 1000)/1000F;
       ```
       **Note:** Observe how `double` number 64-bit capacity allows for the allocation of more decimal digits of precision.


       **Note:** The following screenshot (provided for verification purposes) shows all JShell actions required by this practice segment

       ```java
        [oracloedvare practice2] jshell
        |   Welcome to Shell Version 11.0.4
        |   For an introduction type: /help Intro
        jshell> byte x = 2, y = 3, z = 5;
        x ==> 2
        y ==> 3
        z ==> 5

        jshell> z = (byte) (x + y);
        z ==> 5

        jshell> float a = (float) x / y;
        a ==> 0.6666667

        jshell> double b = (double) x / y;
        b ==> 0.6666666666666666

        jshell> b = a;
        b ==> 0.6666666865348816

        jshell> float c = Math.round(b * 1000)/1000F;
        c => 0.667
       ```


1. Declare `char` primitives and perform type-casting and arithmetic operations.
    1. Declare three `char` variables `al`, `a2`, and `a3` initialized with `'a'` character value as just
a character literal, ASCII character code (`141`), and as unicode code (`61`).
       ```java
       char a1 = 'a', a2 = '\141', a3 = '\u0061';
       ```
       **Note:** In fact all three variables have exactly the same value. They store the code for
'a' character. You can choose to represent this value in ASCII or unicode.

    1. Assign the `a1` variable value to a new int variable `i`:
       ```java
       int i = a1;
       ```
       **Notes**
        - No actual type casting is required to assign a `char` value to an `int` type variable,
because `char` is merely storing a character code that is actually a number.
        - It may be interesting to see what is the octal and hex equivalents of this integer
value. You can convert the variable `i` to text that represents its value as octal (base 8) and hex (base 16) numbers:
          ```java
          Integer.toOctalString(i);
          Integer.toHexString(i);
          ```
        - These two statements produce an output of 141 and 61.
        - Apparently an ASCII character code is an octal number and unicode is hex.
        - Integer is one of primitive wrapper classes supplied with JDK Details of what
wrapper classes are and how to use them are covered in the lesson titled "Text Date Time, and Numeric Objects".

    1. Declare two `int` variables `11` and `12` initialized with `0141` and `0x61` numeric literal values.
       ```java
       int i1 = 0141, i2 - Ox61;
       ```
       **Note:** You can see that these values correspond to decimal value of 97.

    1. Assign `i1` variable value to a new `int` variable `a4`.
       ```java
       char a4 = (char) i1;
       ```
       **Note:** This time type casting is required, because int value could be up to 32 bits in size and `char` capacity is only 16 bits.

**Note:** The following screenshot (provided for verification purposes) shows all JShell actions required by this practice segment:

```java
jshell> char a1 = 'a', a2 = '\141', a3-'\u0061';
a1 ==> 'a'
a2 ==> 'a'
a3 ==> 'a'

jshell> int i = a1;
i ==> 97

jshell> int i1 = 0141, i2 = 0x61;
i1 ==> 97
i2 ==> 97

jshell> char a4 = (char) i1;
a4 ==> 'a'
```

4. Write a check using a remainder of division (modulus) operator to determine if a given char
value is an oven or odd character in the alphabet. Consider that character codes for a and 'z' have decimal integer values of 97 and 122.
    1. Declare a variable `someChar` type of `char` and assign any character value to it
between `a` and `z` inclusive.
       ```java
       char someChar = 'k';
       ```
    1. Apply remainder of division (modulus) operator to `someChar` to determine if it is
divisible by `2` (even number) without remainder of division. Compare the remainder of division to zero. Assign the result of the calculation to a new boolean variable called `isEven`.
       ```java
       boolean isEven = (someChar % 2 == 0);
       ```
       **Notes**
        - JShell allows you to write and evaluate expressions directly without a need to
create extra intermediate variables, so the following code would actually produce an identical verification result: `'k' % 2 == 0;`.
        - Also, the use of round brackets around the boolean expression is actually optional, but it may enhance code readability

**Note:** The following screenshot (provided for verification purposes) shows all actions required by this practice segment:

```java
jshell> char someChar = 'k';
someChar ==> 'k'

jshell> boolean isEven = (someChar % 2 == 0);
isEven ==> false

```

5. Calculate the next and previous characters from a given character. a Set variable someChar type of char to any char value between 'a' and 'y'
inclusive (just before the last character in the alphabet).
someChar - b. Create variable next Char type of char and set it to the value of the next character
that you calculate from the value of the someChar variable. char next Char - (char) (someChar 1); Notes
It is interesting to see which character is going to be derived if the value of someChar is set to 'z'. Your code basically goes beyond alphabetic characters and returns a next char symbol value, which in ASCII is a ''' symbol. You will get an error you try to assign someChart to the nextchar variable without type casting. That is because any arithmetic operation on types smaller than int in this case, a char) will result in an int value and the statement here is
trying to assign it to a char variable. Try another approach at calculating the next character using an increment operator. Reassign the nextChar variable to the value of the next character that you calculate from the value of someChar variable char next Char - someChar; Notes • No type casting is required this time, because operators such as + or -
recursively modify same variable, without changing its type. • If you repeat this line of code again, notice that a value of the variable keeps
progressing to the next char. You can simply press the up arrow on the keyboard
to invoke the previously executed JShell commands.) d. Set someChar to the value of 'b'.
someChar - 'b';
Decrement a value of someChar variable --some Char; Notes • There is no need to create additional variables to hold the next or previous char
values when you derive them using increment or decrement operators, because they recursively modify the original variable anyway. If you repeat this line of code again several times, notice that a value of the variable keeps progressing to the previous character and eventually arrives at uppercase Z.
Note: The following screenshot (provided for verification purposes) shows all JShell actions required by this practice segment:
shell char soneChark soneChar -
:
shell> boolean 1sEven - someChar2 - 0); isEven -> false
shell someChar - 'R' SoneChar -
shell char nextChar - (char) (someChar-1); next Char ''
shell char nextChar - next Char ''
someChar;
shell someChar - 'D': soneCharb
. SomeChar:
shell $22 ->
6. Calculate the number of symbols (char codes) between a pair of characters. a Dafine two char variables for the uppercase 'A' and the lowercase 'a'
char upper - 'A', lower -'a'; b. Calculate how many symbols are between lower and uppercase a characters
int distance - lower-upper, Note: Equivalent upper and lowercase letters are 32 symbols apart in the English alphabet, according to the ASCII character encoding. Set someChar to the value of 'h'.
someChar- h; d. Create another char variable called upper SomeChar to contain an uppercase
equivalent of the someChar variable: char upperSomeChar - (char) (someChar-32); Note: Knowing a distance between upper and lowercase characters for a given alphabet in this case, expressed as English ASCII char codes) can be helpful in writing character case conversions. Of course, such algorithms are already available in existing JDK classes. In this case, a String class has methods to UpperCase and to LowerCase that can convert text to a required case. However, the use of the String class and text formatting is covered in the lesson titled Text, Date, Time, and Numeric Objects.
Note: The following screenshot (provided for verification purposes) shows all JShell actions required by this practice segment:
shell upper Lover
char upper - 'A', lower-'a': - A -
shell> int distance - lower A-upper; distance - 32
shell someChar SonCharh
'h'
shell char upperSoneChar - (char) (soneChar-32): upper SoneChar - H