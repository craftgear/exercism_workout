module Isogram (isIsogram) where

import           Data.Char
import           Data.List

isIsogram :: String -> Bool
isIsogram xs =
  (length . noHyphenAndSpace) xs
    == (length $ nub $ noHyphenAndSpace $ map toLower xs)
  where noHyphenAndSpace = filter (/= ' ') . filter (/= '-')
