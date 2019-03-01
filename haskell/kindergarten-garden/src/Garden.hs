module Garden
    ( Plant (..)
    , garden
    , lookupPlants
    ) where

import           Data.List (find)

data Plant = Clover
           | Grass
           | Radishes
           | Violets
           deriving (Eq, Show)

type Garden = [(String,[Plant])]

charToPlant :: Char -> Plant
charToPlant 'C' = Clover
charToPlant 'G' = Grass
charToPlant 'R' = Radishes
charToPlant 'V' = Violets
charToPlant _   = error ("unknown plant")

pairsFromString :: String -> [String]
pairsFromString [] = []
pairsFromString xs = take 2 xs : pairsFromString (drop 2 xs)

gardenRows :: String -> [[String]]
gardenRows = map pairsFromString . lines

gardenRowsToColumns :: [[String]] -> [String]
gardenRowsToColumns (x:[]) = []
gardenRowsToColumns rows   = zipWith (++) row1 row2
{- zipWithの代わりにApplicative functor使えるのでは？ -> 使えなかった-}
{- gardenRowsToColumns rows   = (++) <$> row1 <*> row2 -}
 where
  row1 = head rows
  row2 = rows !! 1


garden :: [String] -> String -> Garden
garden students plants = zipWith
  (\student pots -> (student, map charToPlant pots))
  students
  potsList
  where potsList = gardenRowsToColumns $ gardenRows plants


lookupPlants :: String -> Garden -> [Plant]
lookupPlants student garden = case found of
  Just (name, plants) -> plants
  Nothing             -> []
  where found = find (\(name, plants) -> name == student) garden
