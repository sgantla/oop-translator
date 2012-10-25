package oop.tree;

import xtc.type.*;

public class PrimitiveType extends Type {

    enum PrimitiveEnum {
	BOOL,
	SCHAR,      // byte
	CHAR,       // char
	SSHORT,     // short
	SINT,       // int
	SLONG,      // long
	FLOAT,      // float
	DOUBLE;     // double
    }

    public Type.Tag tag() {

        return null;
    }
    public Type copy() {
        return null;
    }

}
