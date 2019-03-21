module DNA (toRNA) where

toRNA :: String -> Either Char String
toRNA xs = foldr translate (Right "") xs

{- I don't come up with a solution without recursion. -}
{- Is there any way to eliminate recursion?  -}
translate :: Char -> Either Char String -> Either Char String
translate x accum = case x of
  'C'       -> fmap (\xs -> 'G' : xs) accum
  'G'       -> fmap (\xs -> 'C' : xs) accum
  'T'       -> fmap (\xs -> 'A' : xs) accum
  'A'       -> fmap (\xs -> 'U' : xs) accum
  otherwise -> Left x
