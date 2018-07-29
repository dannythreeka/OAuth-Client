package com.danny.core

interface OAuthClient {

    fun authorizeUser(client: OAuthClientConfig): String

    fun newTokenRequest(client: OAuthClientConfig, authorizationCode: String): String
}