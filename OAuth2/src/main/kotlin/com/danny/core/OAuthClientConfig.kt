package com.danny.core

/**
 * OAuth Client config contains configuration
 *
 * @author Danny Wang <dannythreekai@gmail.com>
 */
class OAuthClientConfig(private val param: Map<String, String>){
    
    companion object Builder {
        private val param = HashMap<String, String>()

        fun setClientId(clientId: String): Builder{
            param[OAuthConst.CLIENT_ID] = clientId
            return this
        }

        fun setClientSecret(clientSecret: String): Builder{
            param[OAuthConst.CLIENT_SECRET] = clientSecret
            return this
        }

        fun setScope(scope: String): Builder{
            param[OAuthConst.SCOPE] = scope
            return this
        }

        fun setRedirectUri(redirectUri: String): Builder{
            param[OAuthConst.REDIRECT_URI] = redirectUri
            return this
        }

        fun build(): OAuthClientConfig = OAuthClientConfig(param)
    }

    fun getClientId(): String?{
            return param[OAuthConst.CLIENT_ID]
    }

    fun getClientSecret(): String?{
        return param[OAuthConst.CLIENT_SECRET]
    }

    fun getScope(): String?{
        return param[OAuthConst.SCOPE]
    }

    fun getRedirectUri(): String?{
        return param[OAuthConst.REDIRECT_URI]
    }
}