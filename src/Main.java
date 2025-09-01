import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pessoa{
    private int id;
    private String nome;
    private int idade;
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
  
    public int getIdade() {
        return idade;
    }
    public Pessoa(Builder builder){//Construtor que recebe um Builder e inicializa os atributos da classe com os valores definidos no Builder
        this.id = builder.id;
        this.nome = builder.nome;
        this.idade = builder.idade;
    }
    public String toString(){
        return "ID:"+id+"|" +"\s" +"Nome:"+nome +"|" +"\s" +"Idade:"+idade;
    }

public static class Builder{
    private int id;
     private String nome;
    private int idade;
    public Builder id(int id){this.id = id; return this;}
    public Builder nome(String nome){this.nome = nome; return this;}
    public Builder idade(int idade){this.idade = idade; return this;}
    public Pessoa build(){ return new Pessoa(this);}
}}

public class Main{
    static List<Pessoa> listaDePessoa = new ArrayList<>();
        public static void main(String[] args) {
            if(args.length > 0 && args[0].equals("test")){
                Test();
                return;
            }
            int op ;
             Scanner sc = new Scanner(System.in);
             do {
                    System.out.println("Menu");
                    System.out.println("1-Cadastrar Pessoa");
                    System.out.println("2-Buscar pessoa pelo nome");
                    System.out.println("3-Listar todas as pessoas");
                    System.out.println("4-Sair");
                    System.out.print("Escolha sua opção:");
                    op = sc.nextInt();
                    switch (op) {
                        case 1->cadastrar(sc);
                            
                        case 2->buscar(sc);
                        case 3->listar();
                        case 4-> System.out.println("Programa encerrado");
                        default->System.out.println("Opção inválida");
                            
                    }
             } while (op!=4);
             sc.close();
        }
    
        public static void cadastrar(Scanner sc){sc.nextLine();
              System.out.print("Digite o ID da Pessoa:");
            int id = sc.nextInt();
            if(listaDePessoa.stream().anyMatch(pessoa-> id == pessoa.getId())){
                System.out.println("Pessoa já cadastrada");
                return;
            }
            System.out.print("Digite o nome da Pessoa:");
            String nome = sc.nextLine();
            
            System.out.print("Digite a idade:");
            int idade = sc.nextInt();
            Pessoa pessoa = new Pessoa.Builder().id(id).nome(nome).idade(idade).build();
            listaDePessoa.add(pessoa);
            System.out.println("Pessoa cadastrada com Sucesso!!");
            System.out.println("=================================");
    }
    public static void buscar(Scanner sc){
        if (listaDePessoa.isEmpty()) {
            System.out.println("Lista vazia");
            return;
        }sc.nextLine();
        System.out.print("Digite o nome da Pessoa:");
            int id = sc.nextInt();
            
           listaDePessoa.stream().filter(pessoa -> id == pessoa.getId()).forEach(System.out::println);
    }
    public static void listar(){
        for (Pessoa pessoa : listaDePessoa) {
            System.out.println(pessoa);
             System.out.println("=================================");
        }
    }
    public static void Test(){
        listaDePessoa.clear();
        listaDePessoa.add(new Pessoa.Builder().id(1).nome("Henrique").idade(22).build());
        listaDePessoa.add(new Pessoa.Builder().id(2).nome("Maria").idade(22).build());
        System.out.println("==Lista de Pessoas==");
        listar();
    }
}