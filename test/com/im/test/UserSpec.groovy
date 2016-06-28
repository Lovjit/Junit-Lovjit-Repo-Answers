package com.im.test

import spock.lang.Specification

class UserSpec extends Specification {

    def "Full name is concatenation of first name and last name along with a space"(){
        setup:
        User user = new User(firstName: fName , lastName: lName)
        when:
        String result = user.fullName
        then:
        result == expectedOutput
        where:
        fName | lName | expectedOutput
        "Lovjit" | "Bedi" | "Lovjit Bedi"

    }

    def "Display name includes Salutation with Full Name"(){
        setup:
        User user = new User(firstName : fName , lastName: lName , gender: gender)
        when:
        String result = user.displayName()
        then:
        result == expectedOutput
        where:
        fName | lName | gender | expectedOutput
        "Lovjit" | "Bedi" | "Male" | "MrLovjit Bedi"
        "Harman" | "Bedi" | "Female" | "MsHarman Bedi"

    }

    def "A Valid Password must have length greater than 7 and should not be empty or null"(){
        setup:
        User user = new User(password: pwd)
        when:
        boolean result = user.isValidPassword(pwd)
        then:
        result == expectedOutput
        where:
        pwd | expectedOutput
        "pwd123" | false
        "pwd12345" | true
        "" | false
        null | false

    }

    def "Testing of resend password and sending email for resetting password"(){
        setup:
        User user = Spy(User)
        user.encyryptPassword(_ as String) >> "New Dummy Password"
        def emailService = Mock(EmailService)
        user.emailService = emailService

        when:
        user.resetPasswordAndSendEmail()
        then:
        user.password == "New Dummy Password"

    }

    def "Testing of password encryption"(){
        setup:
        User user = new User(password: pwd)
        def passwordEncrypterService = Mock(PasswordEncrypterService)
        user.passwordEncrypterService = passwordEncrypterService
        passwordEncrypterService.encrypt(_ as String) >> "Encrypted Pwd"
        when:
        String encryptedPwdActual = user.encyryptPassword(pwd)
        then:
        encryptedPwdActual == encryptedPwdExpected
        where :
        pwd | encryptedPwdExpected
        null | null
        "test12345" | "Encrypted Pwd"
    }

    def "Testing Income Group based on the income per month"(){
        setup:
        User user = new User(incomePerMonth: incomePerMonth)
        when:
        String incomeGroupActual = user.getIncomeGroup()
        then:
        incomeGroupActual == incomeGroupExpected
        where :
        incomePerMonth | incomeGroupExpected
        4520 | "MiddleClass"
        5600 | "Higher MiddleClass"
        54878 | "Very High MiddleClass"
    }

    def "Testing addition of purchased product inside the purchased products list"(){
        setup:
        User user = new User(purchasedProducts: [])
        Product product = new Product()
        product.name = "Vodka"
        when:
        user.purchase(product)
        then:
        user.purchasedProducts.size() == 1
        and:
        user.purchasedProducts.get(0).name == "Vodka"
    }

    def "Testing cancellation of purchased product from the purchased products list"(){
        setup:
        Product product = new Product()
        product.name = "Vodka"
        User user = new User(purchasedProducts: [product])
        when:
        user.cancelPurchase(product)
        then:
        user.purchasedProducts.size() == 0
    }


    def "Testing sorting of categories by interest"(){
        setup:
        User user = Spy(User)
        user.getInterestedInCategories() >> ["C","B","A"]
        when:
        List<String> sortedCategoriesActual = user.getSortedInterestedInCategories()
        then:
        sortedCategoriesActual == ["A","B","C"]
    }

}
