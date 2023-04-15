var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
    always (some f : File | some f.link implies after eventually f in Trash)
}

run  prop8_faulty