package com.bonitasoft.archetype.test;

import groovy.json.JsonSlurper

import java.util.logging.Logger

import javax.servlet.http.HttpServletRequest

import com.bonitasoft.web.extension.rest.RestAPIContext
import org.bonitasoft.web.extension.ResourceProvider
import org.bonitasoft.web.extension.rest.RestApiResponse
import org.bonitasoft.web.extension.rest.RestApiResponseBuilder

import spock.lang.Specification

class IndexTest extends Specification {

    def request = Mock(HttpServletRequest)
    def resourceProvider = Mock(ResourceProvider)
    def context = Mock(RestAPIContext)
    def responseBuilder = new RestApiResponseBuilder()

    def index = Spy(Index)

    def "should return a json representation as result"() {
        request.parameterNames >> (["userId", "startDate"] as Enumeration)
        request.getParameter("userId") >> "aValue1"
        request.getParameter("startDate") >> "aValue2"

        context.resourceProvider >> resourceProvider
        resourceProvider.getResourceAsStream("configuration.properties") >> index.class.classLoader.getResourceAsStream("configuration.properties")

        when:
        index.doHandle(request, responseBuilder, context)

        then:
        def returnedJson = new JsonSlurper().parseText(responseBuilder.build().response)
        //Assertions
        returnedJson.userId == "aValue1"
        returnedJson.startDate == "aValue2"
        returnedJson.hostName == "bonitasoft.com"
    }

    def "should return an error response if userId is not set"() {
        request.parameterNames >> (["userId", "startDate"] as Enumeration)
        request.getParameter("userId") >> null
        request.getParameter("startDate") >> "aValue2"

        context.resourceProvider >> resourceProvider
        resourceProvider.getResourceAsStream("configuration.properties") >> index.class.classLoader.getResourceAsStream("configuration.properties")

        when:
        index.doHandle(request, responseBuilder, context)

        then:
        RestApiResponse restApiResponse = responseBuilder.build()
        def returnedJson = new JsonSlurper().parseText(restApiResponse.response)
        //Assertions
        restApiResponse.httpStatus == 400
        returnedJson.error == "the parameter userId is missing"
    }

    def "should return an error response if startDate is not set"() {
        request.parameterNames >> (["userId", "startDate"] as Enumeration)
        request.getParameter("startDate") >> null
        request.getParameter("userId") >> "aValue1"

        context.resourceProvider >> resourceProvider
        resourceProvider.getResourceAsStream("configuration.properties") >> index.class.classLoader.getResourceAsStream("configuration.properties")

        when:
        index.doHandle(request, responseBuilder, context)

        then:
        RestApiResponse restApiResponse = responseBuilder.build()
        def returnedJson = new JsonSlurper().parseText(restApiResponse.response)
        //Assertions
        restApiResponse.httpStatus == 400
        returnedJson.error == "the parameter startDate is missing"
    }

}