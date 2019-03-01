module Garden
    ( Plant (..)
    , garden
    , lookupPlants
    ) where

data Plant = Clover
           | Grass
           | Radishes
           | Violets
           deriving (Eq, Show)

type Garden = [(String,String)]

charToPlant :: Char -> Plant
charToPlant 'C' = Clover
charToPlant 'G' = Grass
charToPlant 'R' = Radishes
charToPlant 'V' = Violets
charToPlant _   = error ("unknown plant")

pairsFromString :: String -> [String]
pairsFromString [] = []
pairsFromString xs = take 2 xs : pairsFromString (drop 2 xs)

decodeGardenString :: String -> [String]
{- zipWithの代わりにApplicative functor使えるのでは？ -}
decodeGardenString xs = zipWith (\x y -> x ++ y) (rows !! 0) (rows !! 1)
  where rows = map pairsFromString $ lines xs

garden :: [String] -> String -> Garden
garden students plants = error "You need to implement this function."

lookupPlants :: String -> Garden -> [Plant]
lookupPlants student garden = error "You need to implement this function."
