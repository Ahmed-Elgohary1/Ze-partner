package com.Elgo.Zepartner.model.KDtree;

import com.Elgo.Zepartner.model.Address;
import com.Elgo.Zepartner.model.Partner;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
@Data
@NoArgsConstructor
public class Node {

    private final int k=3;

    Node left;
    Node right;
    Partner point;

    final double[] p = new double[k];

    Node(final double latitude, final double longitude) {
        p[0] = (Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(longitude)));
        p[1] = (Math.cos(Math.toRadians(latitude)) * Math.sin(Math.toRadians(longitude)));
        p[2] = (Math.sin(Math.toRadians(latitude)));
    }

    Node(final Partner point) {
        this(point.getAddress().getLatitude(), point.getAddress().getLongitude());
        this.point = point;
    }

    double euclideanDistance(final Node that) {
        final double x = this.p[0] - that.p[0];
        final double y = this.p[1] - that.p[1];
        final double z = this.p[2] - that.p[2];
        return x * x + y * y + z * z;
    }

    double verticalDistance(final Node that, final int axis) {
        final double d = this.p[axis] - that.p[axis];
        return d * d;
    }

    protected static Comparator<Node> getComparator(final int i) {
        return NodeComparator.values()[i];
    }
}




 enum NodeComparator implements Comparator<Node> {
    X {
        @Override
        public int compare(final Node a, final Node b) {
            return Double.compare(a.p[0], b.p[0]);
        }
    },
    Y {
        @Override
        public int compare(final Node a, final Node b) {
            return Double.compare(a.p[1], b.p[1]);
        }
    },
    Z {
        @Override
        public int compare(final Node a, final Node b) {
            return Double.compare(a.p[2], b.p[2]);
        }
    }
}
