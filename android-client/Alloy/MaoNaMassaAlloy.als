module maoNaMassa

// TEMPO
open util/ordering[Time] as to
sig Time{}

//SISTEMA
sig Sistema{
	receitas: set Receita -> Time, 
	usuarios: set Usuario -> Time
}

sig Usuario {
	nomeUsuario: one Nome,	
	livro : one Livro
}

sig Livro{
	receitasDoLivro : set Receita ->Time
}

sig Receita{
	dono : one Usuario,
	ingredientes : set Ingrediente,
	steps : set Step
}

sig Nome{}
sig Ingrediente{}
sig Step{}


// FATOS
fact fatosSistema {
	#Sistema = 1
}
/**
fact fatosPessoas {
	all i : Idade | one i.~idadeCliente
	all c: Cliente, t: Time | lone c.~(clientes.t)
	all n: Nome | one n.~nomeAdm || one n.~nomeCliente || one n.~nomeFunc
	all e: Endereco| one e.~endAdm || one e.~endCliente || one e.~endFunc
	all f: Funcionario | one f.~funcionarios
	all a: Administrador | one a.~administracao	
}*/

fact fatosReceitas {
//	all r : receitas, t : Sistema | receitas.dono in s.usuarios
//	all l : livros, s : Sistema | l.~livro in s.usuarios
}


fact traces {
	init[first]
	all pre: Time-last | let pos = pre.next |
		some sm: Sistema, rec: Receita,  u:Usuario |
				(addReceita[sm, u, rec, pre, pos]  or
				cadastraUsuario[sm, u, pre, pos] or addRecNoLivro[sm, u, rec, pre, pos])


}





// Logica temporal

pred init[t: Time] {
	one Sistema
	no (Sistema.receitas).t
	no (Sistema.usuarios).t
}
// PREDICADOS

pred addReceita[sm: Sistema, c: Usuario, r: Receita, t, t': Time] {
	r ! in (sm.receitas).t
	(sm.receitas).t' = (sm.receitas).t + r
} 

pred cadastraUsuario[sm: Sistema, c: Usuario, t, t': Time] {
	c !in (Sistema.usuarios).t
	(Sistema.usuarios).t' = (Sistema.usuarios).t + c
}


pred addRecNoLivro[sm: Sistema, c: Usuario, r: Receita,  t, t': Time] {
	c in (Sistema.usuarios).t
	r in (Sistema.receitas).t
	r !in ((c.livro).receitasDoLivro).t
	(c.livro.receitasDoLivro).t' = (c.livro.receitasDoLivro).t + r
}



/*
// ASSERTS
assert todoAbrigoTemUmAdministrador {
	all a:Abrigo | one a.administracao
}

assert todoAbrigoTemPeloMenosUmFuncionario {
	all a:Abrigo | #a.funcionarios > 0
}

assert animalAdotadoNaoPertenceANenhumAbrigo{
	all a:Animal, t:Time, ab:Abrigo, c:Cliente | a in ab.(animaisDoAbrigo.t) => a not in c.(animaisAdotados.t)
}

check todoAbrigoTemUmAdministrador for 5
check todoAbrigoTemPeloMenosUmFuncionario for 5
check animalAdotadoNaoPertenceANenhumAbrigo for 5
*/
// main
pred show[]{}

run show for 5 but  3 Usuario

//assinaturas (conjuntos e relações)
//fatos (invariantes)
//predicados e funções
//asserções
//run
//check

//Definição de 5 assinaturas, pelo menos uma herança
//Definição de 5 predicados e 3 funções
//Definição de 5 operações que simulam o comportamento temporal do sistema (usar assinatura Time)
//Definição e verificação de 3 asserts (testes sobre o sistema)
