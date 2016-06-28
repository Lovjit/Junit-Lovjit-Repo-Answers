package com.im.test

import spock.lang.Unroll

class PasswordEncrypterServiceSpec extends spock.lang.Specification {

    def "Testing password encrypter service"(){

            setup:
            PasswordEncrypterService passwordEncrypterService = new PasswordEncrypterService()
            when:
            String encryptedPasswordActual = passwordEncrypterService.encrypt(pwd)
            then:
            encryptedPasswordActual != pwd
            where:

            pwd << ["ABC","DEF"]

    }


}
