import PropTypes from "prop-types"

export const ProductDetail = ({handlesProductSelected, handlerRemove, product = {}}) => (
    <tr key={product.name}>
        <td>{product.name}</td>
        <td>{product.description}</td>
        <td>{product.price}</td>
         <td>
            <button onClick={ () => handlesProductSelected(product) } className="btn btn-secondary btn-sm">
                update
            </button>
        </td>
        <td>
            <button onClick={ () => handlerRemove(product.id) } className="btn btn-danger btn-sm">
                remove
            </button>
        </td>
    </tr>
)

ProductDetail.prototype = {
    product: PropTypes.object.isRequired,
    handlerRemove:PropTypes.func.isRequired,
    handlesProductSelected:PropTypes.func.isRequired
}