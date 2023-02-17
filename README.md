# Ze-partner
## Service 
In Zé we thrive to find our best partner to deliver beverages to our customers, providing the best and fastest service. To achieve this our compute fleet deals with GIS objects all the time.

## Functional Requirements

### 1.1. Create a partner:
Save in a database a partner defined by all the fields represented by the JSON and rules below:
```
{
  "id": 1, 
  "tradingName": "Adega da Cerveja - Pinheiros",
  "ownerName": "Zé da Silva",
  "coverageArea": { 
    "type": "MultiPolygon", 
    "coordinates": [
      [[[30, 20], [45, 40], [10, 40], [30, 20]]], 
      [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
    ]
  },
  "address": { 
    "type": "Point",
    "coordinates": [-46.57421, -21.785741]
  }
}
```

### 1.2. Load partner by id:
Return a specific partner by its `id` with all the fields presented above.

### 1.3. Search partner:
Given a specific location (coordinates long and lat), search the nearest partner which the coverage area includes the location.

## Design Deep Drive

### Data Model
* partner
```
id:Pk
tradingName
ownerName
flavor
type
latitude
longitude
```
* Coordinates
```
id:pk-> referenced to partner.id
x
y
```

### Nearest Neighbour Search

Nearest neighbor search (NNS) is the optimization problem of finding the point in a given set that is closest to a given point.
Using Linear search is the simplest solution to the NNS problem but has a running time of O(dN), where d is the dimensionality.
Another more efficient way is to use space-partitioning methods, such as [ Kd-tree ](https://github.com/Ahmed-Elgohary1/KD-tree)
average complexity is O(log N) in the case of randomly distributed points, worst case complexity is O(kN^(1-1/k)). 
3D-tree implementation is a good algorithm as far as the time complexity is concerned, although for bigger accuracy at calculating the surface distance of points in the Earth “sphere” [Great-circle_distance](https://en.wikipedia.org/wiki/Great-circle_distance)
algorithms can also be used.
