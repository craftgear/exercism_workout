module IsbnVerifier (isbn) where

import           Data.Char

calcCheckSum :: [Int] -> Int
calcCheckSum = sum . zipWith (\x y -> x * y) (reverse [1 .. 10])

isValidCheckSum :: Int -> Bool
isValidCheckSum x = x `mod` 11 == 0

xTo10 :: Char -> String
xTo10 x | x == 'X'  = "10"
        | otherwise = [x]

parseStrToIntList :: String -> [Int]
parseStrToIntList xs = map (strToInt . xTo10) $ filterNumberOrX xs
 where
  strToInt x = read x :: Int
  filterNumberOrX = filter (\x -> isNumber x || x == 'X')

xShouldBeInLastOrNone :: String -> Bool
xShouldBeInLastOrNone xs = not $ 'X' `elem` (init xs)

isbn :: String -> Bool
isbn "" = False
isbn xs = case xShouldBeInLastOrNone xs of
  True  -> validate intList
  False -> False
 where
  intList = parseStrToIntList xs
  validate x =
    if length x == 10 then (isValidCheckSum . calcCheckSum) x else False
