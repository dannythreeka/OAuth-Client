package com.danny.core

class OAuthClientConfig(val param: Map<String, String>){
    
    companion object Builder {
        val param = HashMap<String, String>()

        fun setClientKey(clientKey: String): Builder{
            param.put(OAuthConstant.CLIENT_KEY, clientKey)
            return this
        }

        fun setClientSecret(clientSecret: String): Builder{
            param.put(OAuthConstant.CLIENT_KEY, clientSecret)
            return this
        }

        fun setScope(scope: String): Builder{
            param.put(OAuthConstant.SCOPE, scope)
            return this
        }

        fun setRedirectUri(redirectUri: String): Builder{
            param.put(OAuthConstant.REDIRECT_URI, redirectUri)
            return this
        }

        fun build(): OAuthClientConfig = OAuthClientConfig(param)
    }

    fun getClientKey(): String?{
            return param.get(OAuthConstant.CLIENT_KEY)
    }

    fun getClientSecret(): String?{
        return param.get(OAuthConstant.CLIENT_KEY)
    }

    fun getScope(): String?{
        return param.get(OAuthConstant.SCOPE)
    }

    fun getRedirectUri(): String?{
        return param.get(OAuthConstant.REDIRECT_URI)
    }
}