package com.wechat.alipay.Config;

public class AlipayConfig {
    //支付宝id
    public static String app_id = "2016092500595676";//在后台获取（必须配置）
    //	私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCc4DelXkm79m3eVr/PGNZcMwhv9aQEyeGfLu+qSdUOFJLOHugCmnAV7OsAlB7h4Dz1vSPKLjsIRr6sPpN8T5z78R8kz6eCbI02XtiDws//VhLGUBS7tP72hcu37N/DeZvhY0lZkrmP6WkOpLy8XGRnVDLOcgu0fjIkjcxQzR9nqKl9o3oOMvcSIzUSJu3nwJdF8Ffktk08Gj3qnetwbQ6eVAROijAtP2oJN2N4/XofTme1Coiioe9OQvvIpzA4CFdNBIKpoeVgpg5uqyAu/pdi5z4vb9Y5fY3Sf+MSao+8oYLor2ZRsp7fNFhLe0TR4gIklTs92IKgi+Pg5eDx6isfAgMBAAECggEBAJUiduQn+dLN1+7560jXJNnrzgaZsD65KVuqRD9QB1JmbpWzPf9TkGDXpSUcqysygOFg2MhZINeiYZvftVgFW4kL1f6pxmYwCiL5Eb5ZEmF8E0ciVEOKMqRr15pXfvYXAwDG4bFc0jmkvLImHCK5dt3gZphqRQRmwAaOIwFhr4xbhxUAiZWtlSLaOynQ2i5AE5TFjP8S1AThu693OU1R7X7p//thDZf36fPnmJb4mdBm+DmI7Bmm6Tnn5zw5LayZJXkxocq4Kaj7jbvftwlnGEqiH8ahjPqUk4sY63D+ThJ+lBsYpZDfvty9ICAA7Z9gmCdZQuz3muK6UlWz90OH6SkCgYEAyXwq7IRb4Q856MONZG+Tai+cXLBt3M7l4o+DLqmgJKZr++LbaO4hyXHu1r8xbtqYa18/sQ3i9BztNaf6JxfLFSRlfNNord/KDFl6QEM9adFpH6TFVt21df4e7PE0yl+3L89zW04IELdoqd+060ESMDU7z191g6gEklM3x8YeHcsCgYEAx1Izd3fnZ0SpR9HiggPv8fn5ym2sZKSvQeNFd6QnH2O3CtOxRcX9bxp+BpMLdEjzw+rKYRnmkT6A80F3v0CEDizIemLCD0z/3CKFymftM9ssM5neJxkDUrdhRo8G5ENZ0pi4Snd61944hh6vwZtvyJEYsi8qoDmpr4I05pzM/X0CgYEAkQF56up/LYRFyw0h70yVrXBCejrHdoi4zcs3QBf4FyhmwstL2UK4sj/7AjZrnzpXQ78zGXvDAKjo1xrjX8sx7y9YkvObfLd4tFaZMzKxOjzqCUoOdCvv8tFG9IPL2HH2ZsRIY0fPaih1fPxxOnaEXomRV/d9nZkIYT7fADnHZu0CgYBCvIQc8RmamwOu11hmvdi+BTucioIZJIbFocnlmCG9Qc7MojqNqRAE/jLKErvYEIEFzPw77MclRG86g2YLtBdDA72QNLbTFq3oT+rJ7WgfX7f6Av+9yYMyU9IPJN5i2SYe/CFgVYbc9juotGeWhGu2FNbW+jHkecoVyJvbxw5fpQKBgHObgtx/n+GHgjlJmz/pJufQJ0UlPwpRyndtB5Mhe9SXQLaf9I6gLtVaS1E0O+xtqD+NvZdYV4leKeLfkWyd33kACQy64MkS32Vuq7rJ2qOIwTTa9Vrkgf/kET/lP1GV2HMCwV6vnbiA8IjLqt/BXih58ZD/sdcP1wCvBFRPou/f";//教程查看获取方式（必须配置）
    //   公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8C2CfVRcasht0R0ZLWucDdDKtcozqQNAipOpMTXeG7tpXTavSnTNV3aX04V/GDaKB0Qqecd/oV2rDG7OtWgxNmUesjhlrnkGSJIhB/IL/tRbWeggW51DgqPPMmf7AaNCqcrldc6n+ReZpuqxomORSu3ekKbUsaZfPjNYKtmvy7wZAHTjS/lMdkBG5YJb4NnYdeY3FHWHTfdLY4ZUQ/x7DhV79oU30qf9vQXzCZxW7078IdA4uxFB03SNvE2L1SVJPCIckZA2Xx81Rlu82a7N3HAagcSFzBvKfhKBzQhngi0a8Y0pAU8h8natNuK5jvzc8KKxZo7O2cz3G/reXZoifQIDAQAB";//教程查看获取方式（必须配置）

    public static String notify_url = "http://localhost:8080/alipay/alipay/alipayNotifyNotice.action";

    public static String return_url = "http://localhost:8080/alipay/alipay/alipayReturnNotice.action";

    public static String sign_type = "RSA2";

    //	编码格式  UTF-8
    public static String charset = "utf-8";
    //注意：沙箱测试环境，正式环境为：https://openapi.alipay.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
