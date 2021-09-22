package com.zkzy.portal.base.admin.api.util;

import sun.misc.CharacterEncoder;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class BASE64Encoder extends CharacterEncoder{
    @Override
    protected int bytesPerAtom() {
        return (3);
    }

    @Override
    protected int bytesPerLine() {
        return (57);
    }

    @Override
    protected void encodeAtom(OutputStream outStream, byte data[], int offset,
                              int len) throws IOException {

        byte a, b, c;
        if (len == 1)
        {
            a = data[offset];
            b = 0;
            c = 0;
            outStream.write(pem_array[(a >>> 2) & 0x3F]);
            outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStream.write('=');
            outStream.write('=');
        } else if (len == 2)
        {
            a = data[offset];
            b = data[offset + 1];
            c = 0;
            outStream.write(pem_array[(a >>> 2) & 0x3F]);
            outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStream.write(pem_array[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
            outStream.write('=');
        } else
        {
            a = data[offset];
            b = data[offset + 1];
            c = data[offset + 2];
            outStream.write(pem_array[(a >>> 2) & 0x3F]);
            outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStream.write(pem_array[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
            outStream.write(pem_array[c & 0x3F]);
        }

    }

    private final static char pem_array[] =
            {
                    // 0 1 2 3 4 5 6 7
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', // 0
                    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', // 1
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', // 2
                    'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', // 3
                    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', // 4
                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', // 5
                    'w', 'x', 'y', 'z', '0', '1', '2', '3', // 6
                    '4', '5', '6', '7', '8', '9', '+', '/' // 7
            };
}
