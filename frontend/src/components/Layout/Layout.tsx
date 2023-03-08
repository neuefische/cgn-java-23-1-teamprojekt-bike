import React, {ReactNode, useRef} from "react";
import Header from "../Header/Header";
import AddBike from "../AddBike/AddBike";
import useBikesApi from "../../hooks/useBikesApi";

type Props = {
    children: ReactNode
}
function Layout(props: Props){


    return (
        <>
            <Header addBikeInputRef={addBikeInputRef} />
            <main className="main">
                {props.children}


            </main>

            <footer className="footer">Imprint - 2023</footer>
        </>
    )
}
export default Layout