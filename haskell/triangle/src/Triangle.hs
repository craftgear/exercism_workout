module Triangle (TriangleType(..), triangleType) where

data TriangleType = Equilateral
                  | Isosceles
                  | Scalene
                  | Illegal
                  deriving (Eq, Show)

triangleType :: (Num a, Ord a, Eq a) => a -> a -> a -> TriangleType
triangleType 0 0 0 = Illegal
triangleType a b c | isNotTriangle a b c = Illegal
                   | isScalane a b c     = Scalene
                   | isEquilateral a b c = Equilateral
                   | isIsosceles a b c   = Isosceles
                   | otherwise           = Illegal

isNotTriangle :: (Num a, Ord a) => a -> a -> a -> Bool
isNotTriangle x b c = (x + b) < c || (x + c) < b || (b + c) < x

isScalane :: Eq a => a -> a -> a -> Bool
isScalane x b c = x /= b && x /= c && b /= c

isEquilateral :: Eq a => a -> a -> a -> Bool
isEquilateral x b c = (x == b) && (b == c)

isIsosceles :: Eq a => a -> a -> a -> Bool
isIsosceles x b c = (x == b) || (b == c) || (x == c)
