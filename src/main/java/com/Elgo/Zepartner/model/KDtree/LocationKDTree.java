package com.Elgo.Zepartner.model.KDtree;

import com.Elgo.Zepartner.model.Address;
import com.Elgo.Zepartner.model.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationKDTree {
    private static final int K = 3; // 3-d tree

    private final Node tree;
    private static Node temp=new Node();


    public LocationKDTree(@NonNull final List<Partner> points) {

        final List<Node> nodes = new ArrayList<>(points.size());
        for (final Partner point : points) {
            nodes.add(new Node(point));
        }
        tree = buildTree(nodes, 0);
    }

    @Nullable
    public Partner findNearest(final double latitude, final double longitude) {
        final Node node = findNearest(tree, new Node(latitude, longitude), 0);
        return node == null ? null : node.point;
    }

    private static Node findNearest(final Node current, final Node target, final int depth) {
        final int axis = depth % K;
        final int direction = temp.getComparator(axis).compare(target, current);
        final Node next = (direction < 0) ? current.left : current.right;
        final Node other = (direction < 0) ? current.right : current.left;
        Node best = (next == null) ? current : findNearest(next, target, depth + 1);
        if (current.euclideanDistance(target) < best.euclideanDistance(target)) {
            best = current;
        }
        if (other != null) {
            if (current.verticalDistance(target, axis) < best.euclideanDistance(target)) {
                final Node possibleBest = findNearest(other, target, depth + 1);
                if (possibleBest.euclideanDistance(target) < best.euclideanDistance(target)) {
                    best = possibleBest;
                }
            }
        }
        return best;
    }

    @Nullable
    private static Node buildTree(final List<Node> items, final int depth) {
        if (items.isEmpty()) {
            return null;
        }

        Collections.sort(items, temp.getComparator(depth % K));
        final int index = items.size() / 2;
        final Node root = items.get(index);
        root.left = buildTree(items.subList(0, index), depth + 1);
        root.right = buildTree(items.subList(index + 1, items.size()), depth + 1);
        return root;
    }


    public static class Test {

        public static void main(String[] args){


            Partner point1=Partner.builder()
                    .ownerName("Ahmed")
                    .address(Address.builder()
                            .type("Point")
                            .latitude(40.6333)
                            .longitude(22.9500)
                            .build())

                    .build();

            Partner point2=Partner.builder()
                    .ownerName("said")
                    .address(Address.builder()
                            .type("Point")
                            .latitude(41.6333)
                            .longitude(220.9500)
                            .build())

                    .build();

            Partner point3=Partner.builder()
                    .ownerName("lol")
                    .address(Address.builder()
                            .type("Point")
                            .latitude(4.6333)
                            .longitude(22.500)
                            .build())

                    .build();


            List<Partner>points=new ArrayList<Partner>();
            points.add(point1);
            points.add(point2);
            points.add(point3);

            LocationKDTree pointTree = new LocationKDTree(points);
            Partner nearestPoint = pointTree.findNearest(37.9500, 23.7000); // Piraeus coordinates

            System.out.println(nearestPoint.getOwnerName());
        }
    }



}
