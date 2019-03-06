module ComplexNumbers
(Complex,
 conjugate,
 abs,
 exp,
 real,
 imaginary,
 mul,
 add,
 sub,
 div,
 complex) where

import           Prelude hiding (abs, div, exp)
import qualified Prelude as P

-- Data definition -------------------------------------------------------------
data Complex a = Complex a a deriving(Eq, Show)

{- 型定義に書くのはtype constructorの方 -}
{- [> これはtype constructorのtype variableがaであることを示しているので、引数が一つ <] -}
complex :: Num a => (a, a) -> Complex a
{- 一方、こちらはdata constructorなので、引数が2つ -}
complex (x, y) = Complex x y

-- unary operators -------------------------------------------------------------
conjugate :: Num a => Complex a -> Complex a
conjugate (Complex x y) = Complex x (-y)

abs :: Floating a => Complex a -> a
abs (Complex x y) = sqrt (x ^ 2 + y ^ 2)

real :: Num a => Complex a -> a
real (Complex x y) = x

imaginary :: Num a => Complex a -> a
imaginary (Complex x y) = y

exp :: Floating a => Complex a -> Complex a
exp (Complex x y) = Complex (cos y * P.exp x) (sin y * P.exp x)

-- binary operators ------------------------------------------------------------
mul :: Num a => Complex a -> Complex a -> Complex a
mul (Complex x y) (Complex a b) = Complex (x * a - y * b) (x * b + y * a)

add :: Num a => Complex a -> Complex a -> Complex a
add (Complex x y) (Complex a b) = Complex (x + a) (y + b)

sub :: Num a => Complex a -> Complex a -> Complex a
sub (Complex x y) (Complex a b) = Complex (x - a) (y - b)

div :: Fractional a => Complex a -> Complex a -> Complex a
div (Complex x y) (Complex a b) = Complex ((x * a + y * b) / (a ^ 2 + b ^ 2))
                                          ((y * a - x * b) / (a ^ 2 + b ^ 2))
