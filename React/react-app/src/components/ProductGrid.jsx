import PropTypes from "prop-types"
import { ProductDetail } from "./ProductDetail"

export function ProductGrid({handlesProductSelected, handlerRemove, products =[] }) {
    return (
        <table className="table table-hover table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Update</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                {products.map(product => {
                    return (
                        <ProductDetail 
                            handlesProductSelected={handlesProductSelected} 
                            handlerRemove={handlerRemove} 
                            product={product} 
                            key={product.name}/>
                    )
                })}

            </tbody>
        </table>
    )
}

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired,
    handlerRemove:PropTypes.func.isRequired,
    handlesProductSelected:PropTypes.func.isRequired
}
