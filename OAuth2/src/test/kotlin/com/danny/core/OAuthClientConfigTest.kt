package com.danny.core

import org.junit.Assert.assertEquals
import org.junit.Test

class OAuthClientConfigTest{
    val TEST_CLIENT_KEY = "123"
    val TEST_CLIENT_SECRET = "abc"
    val TEST_CLIENT_SCOPE = "openid"
    val TEST_CLIENT_REDIRECT_URI = "http://danny.com"

    @Test
    fun testOAuthClientConfig(){
        val tester = OAuthClientConfig.setClientKey(TEST_CLIENT_KEY).setClientSecret(TEST_CLIENT_SECRET)
            .setScope(TEST_CLIENT_SCOPE).setRedirectUri(TEST_CLIENT_REDIRECT_URI).build()

        assertEquals(TEST_CLIENT_KEY, tester.getClientKey())
        assertEquals(TEST_CLIENT_SECRET, tester.getClientSecret())
        assertEquals(TEST_CLIENT_SCOPE, tester.getScope())
        assertEquals(TEST_CLIENT_REDIRECT_URI, tester.getRedirectUri())
    }
}