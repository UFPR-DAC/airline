function LoginComponent()
{
    return (<>      
        <div>
            <div>              
                <label >Usuário: </label>
                <input placeholder="Digite seu nome de usuário"></input>            
            </div>
            <div>
                <label>Senha: </label>
                <input type="password" placeholder="******"></input>
            </div>
            <div>
                <button className="button-primary">Login</button>
                <button>Cadastrar</button>
            </div>           
        </div>
    </>)
}

export default LoginComponent