import Login from "../../components/Login/Login";
export default function LoginComponent() {
    return (

        <div
            className="relative bg-cover bg-center min-h-screen w-full bg-no-repeat bg-fixed"
            style={{
                backgroundImage: "url('/images/raphael-nogueira-unsplash.jpg')",
            }}>

            <div className="min-h-screen flex items-center justify-center">
                <Login />
            </div>
        </div>
    );
}