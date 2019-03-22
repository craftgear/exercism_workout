module DNA (toRNA) where

toRNA :: String -> Either Char String
toRNA xs = foldr translate (Right "") xs

translate :: Char -> Either Char String -> Either Char String
translate x accum = case x of
  'C'       -> fmap (\xs -> 'G' : xs) accum
  'G'       -> fmap (\xs -> 'C' : xs) accum
  'T'       -> fmap (\xs -> 'A' : xs) accum
  'A'       -> fmap (\xs -> 'U' : xs) accum
  otherwise -> Left x
