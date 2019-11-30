package hr.fer.teslasjourney.data.interactors

interface BaseInteractor<in Request, out Response> {

    fun execute(req: Request): Response
}