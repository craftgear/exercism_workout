module ProteinTranslation(proteins) where
import           Data.List.Split

proteins :: String -> Maybe [String]
proteins =
  Just . takeWhile (\x -> x /= "STOP") . map codonToProtein . rnaToCodons

rnaToCodons :: String -> [String]
rnaToCodons "" = []
rnaToCodons xs = chunksOf 3 xs

codonToProtein :: String -> String
codonToProtein codon
  | codon == "AUG"
  = "Methionine"
  | codon == "UUU" || codon == "UUC"
  = "Phenylalanine"
  | codon == "UUA" || codon == "UUG"
  = "Leucine"
  | codon == "UCU" || codon == "UCC" || codon == "UCA" || codon == "UCG"
  = "Serine"
  | codon == "UAU" || codon == "UAC"
  = "Tyrosine"
  | codon == "UGU" || codon == "UGC"
  = "Cysteine"
  | codon == "UGG"
  = "Tryptophan"
  | codon == "UAA" || codon == "UAG" || codon == "UGA"
  = "STOP"
