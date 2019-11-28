package com.example.backend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@SpringBootTest
@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"pretty"})
public class runTest {
}
