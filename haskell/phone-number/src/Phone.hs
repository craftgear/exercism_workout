module Phone (number) where

import           Data.Char
import           Debug.Trace

headNumber x | x == '0'  = False
             | x == '1'  = False
             | otherwise = True

validAreaCode :: String -> String
validAreaCode ""            = ""
validAreaCode origin@(x:xs) = case valid of
  True  -> origin
  False -> ""
  where valid = headNumber x

validExchangeCode :: String -> String
validExchangeCode xs = case valid of
  True  -> xs
  False -> ""
  where valid = headNumber (xs !! 3)

isValid :: String -> String
isValid origin@(x:xs)
  | length origin == 10 = (validAreaCode . validExchangeCode) origin
  | length origin == 11 = case x of
    '1' -> isValid xs
    _   -> ""
  | otherwise = ""

number :: String -> Maybe String
number xs = case length scrubbedNumber of
  10 -> Just scrubbedNumber
  _  -> Nothing
 where
  scrubbedNumber = scrub xs
  scrub          = reverse . take 10 . reverse . isValid . filter isNumber
