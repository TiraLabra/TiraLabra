package trie

func TestBasics(*testing.T) {
	t := trie.CreateNode()
	t.Add("hajoo", "HAJOO")
	t.Add("aakkos", "AAKKOS")
	t.Add("aakkosia", "AAKKOSIA")
	t.Add("haju", "HAJU")
	t.Add("soppaako", "SOPPAAKO")
	fmt.Println(t.Get("hajoo"))
	fmt.Println(t.Get("aakkos"))
	fmt.Println(t.Get("aakkosia"))
	fmt.Println(t.Get("haju"))
	fmt.Println(t.Get("soppaako"))
}
