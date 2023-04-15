var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop17_faulty { 
  all f: File | always (File' = File - f since eventually f in Trash)

}

run  prop17_faulty