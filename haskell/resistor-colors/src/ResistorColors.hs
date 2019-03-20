module ResistorColors (Color(..), value) where

data Color =
    Black
  | Brown
  | Red
  | Orange
  | Yellow
  | Green
  | Blue
  | Violet
  | Grey
  | White
  deriving (Eq, Show, Read)

colorToNumber :: Color -> Int
colorToNumber color = case color of
  Black  -> 0
  Brown  -> 1
  Red    -> 2
  Orange -> 3
  Yellow -> 4
  Green  -> 5
  Blue   -> 6
  Violet -> 7
  Grey   -> 8
  White  -> 9

value :: [Color] -> Int
value cs = sum $ zipWith (\x y -> x * (colorToNumber y)) multipliers cs
  where multipliers = reverse $ map (\x -> 10 ^ x) [0 .. (length cs) - 1]
