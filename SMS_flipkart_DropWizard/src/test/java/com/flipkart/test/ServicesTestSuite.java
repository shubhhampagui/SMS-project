package com.flipkart.test;

import com.flipkart.test.service.CourseManagementServiceTest;
import com.flipkart.test.service.NotificationServiceTest;
import com.flipkart.test.service.PaymentServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The type Services test suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({NotificationServiceTest.class, CourseManagementServiceTest.class, PaymentServiceTest.class})
public class ServicesTestSuite {
}
