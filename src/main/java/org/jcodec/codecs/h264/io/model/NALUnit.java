package org.jcodec.codecs.h264.io.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 * 
 * Network abstraction layer (NAL) unit
 * 
 * @author Jay Codec
 * 
 */
public class NALUnit {

    public NALUnitType type;
    public int nal_ref_idc;

    public NALUnit(NALUnitType type, int nal_ref_idc) {
        this.type = type;
        this.nal_ref_idc = nal_ref_idc;
    }

    public static NALUnit read(InputStream is) throws IOException {
        int nalu = is.read();
        int nal_ref_idc = (nalu >> 5) & 0x3;
        int nb = nalu & 0x1f;

        NALUnitType type = NALUnitType.fromValue(nb);
        return new NALUnit(type, nal_ref_idc);
    }

    public void write(OutputStream out) throws IOException {
        int nalu = type.getValue() | (nal_ref_idc << 5);
        out.write(nalu);
    }
}
