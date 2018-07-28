package com.danny.core

interface OAuthFactory {
    fun authorizationUri(): String

    fun accessTokenUri(): String
}