module Scrabble (scoreLetter, scoreWord) where

import           Data.Char   (toLower)
import           Debug.Trace (trace)

ten = ['q', 'z']
eight = ['j', 'x']
five = ['k']
four = ['f', 'h', 'v', 'w', 'y']
three = ['b', 'c', 'm', 'p']
two = ['d', 'g']
one = ['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't']


scoreLetter :: Char -> Integer
scoreLetter letter | lowerLetter `elem` ten   = 10
                   | lowerLetter `elem` eight = 8
                   | lowerLetter `elem` five  = 5
                   | lowerLetter `elem` four  = 4
                   | lowerLetter `elem` three = 3
                   | lowerLetter `elem` two   = 2
                   | lowerLetter `elem` one   = 1
                   | otherwise                = 0
  where lowerLetter = toLower letter

scoreWord :: String -> Integer
scoreWord = foldr (\current accum -> accum + scoreLetter current) 0
