package com.carlosfunalleras.rest.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "altair123$%";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
	          "MIIEpgIBAAKCAQEA+kute9k4Xlsvem9Fvi9jS48Kyvllt0XUgkl0dRmdOc4elQ64\n" +
	          "6DDnb0+cNpnAnQaPpuWVa+iaTbLrR5VTF6VsKEvVTYJf4/9CxJlpcups6xgQXnP1\n" +
	          "AH+y0Qho2haePJ+tVIROMv/n+Y7JF/CTEcFzx461Q9mutgO46VqcseqSedUKKSA0\n" +
	          "6rqF57zZI4v4ZpmvspGJnTvPy1vuGXe9Gv5Sd4Wy+FVRmgdthbiD5JXzsnLClaky\n" +
	          "9pxeC0By3HSZVCGlLl3JzaDsKoJ7E5wc33CdYNz38xdCGazcREKOH7OWXtCh9MVf\n" +
	          "8T9u6m/9ycNFa9T727verIsxCZJXAQPEv2kOcwIDAQABAoIBAQDHR4epqqoRngIw\n" +
	          "NfXOVYZ2Oz45mtWcVNG8peU98pXW10BMJzubSoyJeFYEtue/XeDz3ajZEIoCQOjI\n" +
	          "xqAwnHl/n9kFbD8SlULAqvBlGTZ8+1zVBvfTXNivS0XIFegIlo13VBu6yTsV29RJ\n" +
	          "5jYCFgcrpHi3u9I5gr8vtAA8O2pB6oSlY+vrdoE5/a/tOLkRvcWs7xvmUwITOQpC\n" +
	          "rdEIspqgCQSq+4rfdb85l6jyrnas8uXj01MRDj+d4Fs1Skk2zmgIgNUIo39kIPJh\n" +
	          "6qejBKZu3d2czig7hrdw+mDoN433AkoZY07bMLI5NSNNgQIrfdI6+0rKKpKfuqZx\n" +
	          "MdKnFWdhAoGBAP5qQURpkinmUftGYxPba3y85HgwZx/18nwclKvjLJCJobtAjon6\n" +
	          "eBn+rv6o9a9929qEAGjlhgTDccjJgzgYoYCu5MRSGj54sLT+k2EHddpBkpMyEMf7\n" +
	          "ioljNY2rzWwo8nfrjKnzKhHBZZcvjsS0kIAPAf6MX+gyGjvNHxtkFGGRAoGBAPva\n" +
	          "2lxIIscV5/cNf0oPbDJO/8mHGBYLCnbIVh+qDAlcN7uCVlXnGU85HN4n6TR5YtpH\n" +
	          "b1zoN5rAwnoaX5NCp9gfrhlNHGaG+1noAoUSlSIZPWPLRAFeP5bdm4bwMLBh0POI\n" +
	          "5e64Zy6weqWfxadrGVcGHbjyjzM5OT9RY05E2G3DAoGBANag3R7R0TT30QGih8Mg\n" +
	          "wLUixbt/WgBkhISft+yqYdYtxfsqoxVT8C495XOBJoM+MlNOOOpFd6JdH4i8N68D\n" +
	          "WHwvD19vxCQfvTNz8PFSUeImn3j+K5oKYv00bOFvpoUwphz04l4dfcgs13+L522a\n" +
	          "xqzB/HS6B87jNa5thWieLXOBAoGBAMipvoD4GH12vrLgViKz1wjeWH5A3LfzZArv\n" +
	          "426nFZ//kYgceJ1I26l7X7zBH9VnQZOYhuoHOwZZvpl6mdvCp+hSlWwjPBWURn+D\n" +
	          "kz5jZ98bLQZt1WkhppiVSG1iwehGhy0CTfC9i7hvc8iyW9DgS/hCLzxERkQRu8Lv\n" +
	          "HagmwinxAoGBALgTDMrvvkrqj2IXXKidyoQcVeiTF7eZ5P3SQkyILhmmcESFme36\n" +
	          "y/Jt9IRoum1MsKjMfM25QypHSCvIoGHjZ4i/bTVbZqu6pigtaESuqaAmJIwo+/lz\n" +
	          "2Kq6Wy27l5NXIk/vmsZe1lN8Oe9l7l8CQJ2q/N7X0T/o/bLW0BRY5yUL\n" +
	          "-----END RSA PRIVATE KEY-----";

	  public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
	          "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA+kute9k4Xlsvem9Fvi9j\n" +
	          "S48Kyvllt0XUgkl0dRmdOc4elQ646DDnb0+cNpnAnQaPpuWVa+iaTbLrR5VTF6Vs\n" +
	          "KEvVTYJf4/9CxJlpcups6xgQXnP1AH+y0Qho2haePJ+tVIROMv/n+Y7JF/CTEcFz\n" +
	          "x461Q9mutgO46VqcseqSedUKKSA06rqF57zZI4v4ZpmvspGJnTvPy1vuGXe9Gv5S\n" +
	          "d4Wy+FVRmgdthbiD5JXzsnLClaky9pxeC0By3HSZVCGlLl3JzaDsKoJ7E5wc33Cd\n" +
	          "YNz38xdCGazcREKOH7OWXtCh9MVf8T9u6m/9ycNFa9T727verIsxCZJXAQPEv2kO\n" +
	          "cwIDAQAB\n" +
	          "-----END PUBLIC KEY-----";

}
