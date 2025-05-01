import ComprarMilhas from "../../../components/ComprarMilhas/ComprarMilhas";

export default function ComprarMilhasComponent() {
    return (
        <>
            <div
                className="relative bg-cover bg-center min-h-screen w-full bg-no-repeat bg-fixed"
                style={{
                    backgroundImage: "url('/images/nils-nedel-unsplash.jpg')",
                }}
            >
                <div className="relative z-10  items-center px-4 min-h-screen">
                    <ComprarMilhas />
                </div>
            </div>
        </>
    );
}