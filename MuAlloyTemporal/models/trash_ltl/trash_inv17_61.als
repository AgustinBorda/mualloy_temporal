var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop17_faulty { 
  all f: File | once f in Trash releases always f' in File

}

run  prop17_faulty